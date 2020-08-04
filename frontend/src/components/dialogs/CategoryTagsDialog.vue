<template>
    <v-dialog v-model="visible" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Метки категории</span>
            </v-card-title>

            <v-card-text>
                <v-container>
                    <v-col cols="6" sm="6" md="6">
                        <v-btn @click="onClose">Закрыть</v-btn>
                    </v-col>

                    <v-row>
                        <v-col cols="6" sm="6" md="6">
                            <v-list two-line subheader class="overflow-y-auto">
                                <v-subheader inset>Выбранные</v-subheader>
                                <v-list-item
                                        v-for="item in category.tags"
                                        :key="item.id"
                                        @click="removeTag(item)"
                                >
                                    <v-list-item-content>
                                        <v-list-item-title v-text="item.name"></v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                        </v-col>

<!--                        todo remove tags that already added-->
                        <v-col cols="6" sm="6" md="6">
                            <v-list two-line subheader class="overflow-y-auto">
                                <v-subheader inset>Доступные</v-subheader>
                                <v-list-item
                                        v-for="item in tags"
                                        :key="item.id"
                                        @click="addTag(item)"
                                >
                                    <v-list-item-content>
                                        <v-list-item-title v-text="item.name"></v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    import {mapState} from 'vuex';

    export default {
        name: 'CategoryTagsDialog',

        props: {
            visible: Boolean,
            onClose: Function,
            category: Object
        },

        computed: {
            ...mapState({
                tags: state => state.tags.tags,
                defaultAccount: state => state.defaultAccount.defaultAccount,
            }),
        },

        methods: {
            addTag(tag) {
                let dto = {
                    'categoryId': this.category.id,
                    'tagId': tag.id
                }

                this.$store.dispatch('insertTagAction', dto)
            },


            removeTag(tag) {
                let dto = {
                    'categoryId': this.category.id,
                    'tagId': tag.id
                }

                this.$store.dispatch('removeTagAction', dto)
            },
        },
    }
</script>

<style scoped>

</style>
