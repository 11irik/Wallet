import vaultApi from '../../api/vault'

export default {
    state: {
        vaults: frontendData.vaults,
    },

    getters: {
        vaults: state => state.vaults,
    },

    mutations: {
        addVaultMutation(state, vault) {
            state.vaults = [
                ...state.vaults,
                vault
            ]
        },

        retrieveVaultsMutation(state, vaults) {
            state.vaults = vaults
        },
    },

    actions: {
        async addVaultAction({ commit, state }, data) {
            let dto = {
                name: data.name,
                sum: data.sum,
                accountId: data.accountId
            }

            let result = await vaultApi.add(dto)
            let response = await result.json()

            commit('addVaultMutation', response)
        },

        async retrieveVaultsAction({ commit, dispatch }, account) {
            let result = await vaultApi.getAccountVaults(account)
            let data = await result.json()

            commit('retrieveVaultsMutation', data)
        },
    }
}
