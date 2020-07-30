import itemApi from '../../api/item';

export default {
    state: {
        items:[],
    },

    getters: {
        items: state => state.items
    },

    mutations: {
        addItemMutation(state, item) {
            state.items = [
                ...state.items,
                item
            ]
        },

        retrieveItemsMutation(state, items) {
            state.items = items
        },

    },

    actions: {
        async addItemAction({ commit, state }, itemDto) {
            const result = await itemApi.add(itemDto)
            const data = await result.json()

            commit('addItemMutation', data)
        },

        async retrieveItemsAction({ commit, dispatch }, transaction) {
            const result = await itemApi.getTransactionItems(transaction)
            const data = await result.json()

            commit('retrieveItemsMutation', data)
        },
    }
}