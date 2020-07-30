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
            const dto = {
                name: data.name,
                sum: data.sum,
                accountId: data.accountId
            }

            const result = await vaultApi.add(dto)
            const response = await result.json()

            commit('addVaultMutation', response)
        },

        async retrieveVaultsAction({ commit, dispatch }, account) {
            const result = await vaultApi.getAccountVaults(account)
            const data = await result.json()

            commit('retrieveVaultsMutation', data)
        },
    }
}