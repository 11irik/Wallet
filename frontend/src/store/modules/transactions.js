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
    }
}