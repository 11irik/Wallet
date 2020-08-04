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
            let result = await tagApi.add(tag)
            let data = await result.json()

            commit('addTagMutation', data)
        },

        async retrieveTagsAction({ commit, dispatch }, account) {
            let result = await tagApi.getAccountTags(account)
            let data = await result.json()

            commit('retrieveTagsMutation', data)
        },
    }
}
