import tagApi from '../../api/tag';

export default {
    state: {
        tags: frontendData.tags,
    },

    getters: {
        tags: state => state.tags
    },

    mutations: {
        addTagMutation(state, tag) {
            state.tags = [
                ...state.tags,
                tag
            ]
        },

        retrieveTagsMutation(state, tags) {
            state.tags = tags
        },
    },

    actions: {
        async addTagAction({ commit, state }, tag) {
            const result = await tagApi.add(tag)
            const data = await result.json()

            commit('addTagMutation', data)
        },

        async retrieveTagsAction({ commit, dispatch }, account) {
            const result = await tagApi.getAccountTags(account)
            const data = await result.json()

            commit('retrieveTagsMutation', data)
        },
    }
}