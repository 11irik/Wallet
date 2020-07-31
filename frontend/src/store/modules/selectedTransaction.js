import userApi from "../../api/user";
import accountApi from "../../api/account";

export default {
    state: {
        selectedTransaction: null,
    },

    getters: {
        selectedTransaction: state => state.selectedTransaction
    },

    mutations: {
        selectTransactionMutation(state, transaction) {
            state.selectedTransaction = transaction
        },
    },

    actions: {
        async selectTransactionAction({ commit, state }, transaction) {
            commit('selectTransactionMutation', transaction)
        },
    }
}