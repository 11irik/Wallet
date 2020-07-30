import transactionApi from '../../api/transaction'

export default {
    state: () => ({
        transactions: [],
    }),

    getters: {
        transactions: state => state.transactions,

        getTransactionItems: state => transaction => {
            return state.transactions.find(item => item.id === transaction.id).items
        },

        sortedTransactions: state => {
            return (state.transactions || []).sort((a, b) => -(a.id - b.id))
        }
    },

    mutations: {
        addTransactionMutation(state, transaction) {
            state.transactions = [
                ...state.transactions,
                transaction
            ]
        },

        retrieveTransactionsMutation(state, transactions) {
            state.transactions = transactions
        },

        updateTransactionMutation(state, transaction) {
            const updateIndex = state.transactions.find(item => item.id === transaction.id)
            Object.assign(updateIndex, transaction)
            state.items = transaction.items
        },
    },

    actions: {
        async addTransactionAction({commit, state}, transaction) {
            const result = await transactionApi.add(transaction)
            const data = await result.json()

            commit('addTransactionMutation', data)
        },

        async retrieveTransactionsAction({commit, state}, dto) {
            const result = await transactionApi.getTransactions(dto)
            const data = await result.json()

            commit('retrieveTransactionsMutation', data)
        },

        async addTransactionItemAction({commit, state}, dto) {
            const result = await transactionApi.addItem(dto)
            const data = await result.json()

            commit('updateTransactionMutation', data)
        },
    }
}