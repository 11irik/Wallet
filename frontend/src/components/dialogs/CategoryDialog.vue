<template>
    <v-dialog v-model="show" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Добавить категорию</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="категория" required v-model="categoryName"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                            <v-btn @click="addCategory">Добавить</v-btn>
                        </v-col>
                    </v-row>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="close">Закрыть</v-btn>
                    </v-card-actions>
                </v-container>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "CategoryDialog",

        props: {
            value: Boolean
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
            ...mapState({
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
                selectedCategory: '',
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