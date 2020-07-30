import transactionTagApi from '../../api/transactionTag';

export default {
    state: {
        transactionTags: frontendData.transactionTags,
    },

    getters: {
        transactionTags: state => state.transactionTags,

    },

    mutations: {
        retrieveTransactionTagsMutation(state, transactionTags) {
            state.transactionTags = transactionTags
        },

    },

    actions: {
        async retrieveTransactionTagsAction({ commit }, account) {
            const result = await transactionTagApi.getAll()
            const data = await result.json()

            commit('retrieveTransactionTagsMutation', data)
        },

    }
}