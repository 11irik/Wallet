import accountApi from '../../api/account'

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
            let updateIndex = state.accounts.findIndex(item => item.id === account.id)

            state.accounts = [
                ...state.accounts.slice(0, updateIndex),
                account,
                ...state.accounts.slice(updateIndex + 1)
            ]
        },
    },

    actions: {
        async addAccountAction({ commit, state }, account) {
            let result = await accountApi.add(account)
            let data = await result.json()
            commit('addAccountMutation', data)
        },
    }
}
