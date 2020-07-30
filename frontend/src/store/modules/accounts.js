import accountApi from '../../api/account'
import userApi from '../../api/user'

export default {
    state: {
        accounts: frontendData.userAccounts,
    },

    getters: {
        accounts: state => state.accounts
    },

    mutations: {
        addAccountMutation(state, account) {
            state.accounts = [
                ...state.accounts,
                account
            ]
        },

        updateAccountMutation(state, account) {
            const updateIndex = state.accounts.findIndex(item => item.id === account.id)

            state.accounts = [
                ...state.accounts.slice(0, updateIndex),
                account,
                ...state.accounts.slice(updateIndex + 1)
            ]
        },


    },

    actions: {
        async addAccountAction({ commit, state }, account) {
            const result = await accountApi.add(account)
            const data = await result.json()
            commit('addAccountMutation', data)
        },


    }
}