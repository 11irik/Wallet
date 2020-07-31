<template>
    <v-dialog v-model="show" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Метки категории</span>
            </v-card-title>
            <v-card-text>

                <v-container>
                    <v-col cols="6" sm="6" md="6">
                        <v-btn @click="close">Закрыть</v-btn>
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

                        <v-col cols="6" sm="6" md="6">
                            <v-list two-line subheader class="overflow-y-auto">
                                <v-subheader inset>Доступные</v-subheader>
                                <v-list-item
                                        v-for="item in tags"
                                        :key="item.id"
                                        @click="insertTag(item)"
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
    import {mapState} from "vuex";
    //FIXME
    export default {
        name: "CategoryTagsDialog",

        props: {
            value: Boolean,
        },

        computed: {
            show: {
                get() {
                    return this.value
                },
                set(value) {
                    this.$emit('input', value)
                }
            },
            category: () => {
                return {tags: []}
            },
            ...mapState({
                profile: state => state.profile.profile,
                accounts: state => state.accounts.accounts,
                tags: state => state.tags.tags,
                categories: state => state.categories.categories,
                defaultAccount: state => state.defaultAccount.defaultAccount,
            }),

        },

        data() {
            return {
                userEmail: '',
                dialogTag: false,
                dialogCategory: false,
                dialogCategoryTags: false,
                categoryName: '',
                tagName: '',
            }
        },

        methods: {
            addTag() {
                var dto = {
                    "accountId": this.defaultAccount.id,
                    "name": this.tagName
                }
                this.$store.dispatch('addTagAction', dto)
                this.dialogTag = false
                this.tagName = ""
            },

            insertTag(tag) {
                var dto = {
                    "categoryId": this.selectedCategory.id,
                    "tagId": tag.id
                }

                this.$store.dispatch('insertTagAction', dto)
            },

            removeTag(tag) {
                var dto = {
                    "categoryId": this.selectedCategory.id,
                    "tagId": tag.id
                }
                this.$store.dispatch('removeTagAction', dto)
            },


            addCategory() {
                var dto = {
                    "accountId": this.defaultAccount.id,
                    "name": this.categoryName
                }
                this.$store.dispatch('addCategoryAction', dto)
                this.dialogCategory = false
                this.categoryName = ""
            },

            close() {
                this.show = false
            }
        },
    }
</script>

<style scoped>

</style>