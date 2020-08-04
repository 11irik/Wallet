import userApi from '../../api/user';
import accountApi from '../../api/account';

export default {
    state: {
        defaultAccount: frontendData.defaultAccount,
    },

    getters: {
        defaultAccount: state => state.defaultAccount
    },

    mutations: {
        selectAccountMutation(state, account) {
            state.defaultAccount = account
        },
    },

    actions: {
        async selectAccountAction({ commit, state }, account) {
            let result = await userApi.setAccount(account)

            commit('selectAccountMutation', account)
        },

        async inviteUserAction({commit, state}, dto) {

            let result = await accountApi.addUser(dto)
        },
    }
}
