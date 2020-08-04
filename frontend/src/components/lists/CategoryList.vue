<template>
    <div>
        <CategoryDialog :visible="dialogCategory" :onClose="handleCloseCategoryDialog"/>
        <CategoryTagsDialog :visible="dialogCategoryTags" :category="selectedCategory" :onClose="handleCloseCategoryTagsDialog"/>

        <v-card max-width="500" class="mx-auto">
            <v-list>
                <v-list-item-title class="headline mb-1">Категории</v-list-item-title>

                <v-list-item
                        v-for="item in categories"
                        :key="item.id"
                        @click="openCategoryTagsDial(item)"
                >
                    <v-list-item-content>
                        <v-list-item-title v-text="item.name"></v-list-item-title>
                    </v-list-item-content>

                    <v-list-item-icon>
                        <v-icon>mdi-format-align-justify</v-icon>
                    </v-list-item-icon>
                </v-list-item>

<!--                todo think about rules and default users abilities-->
                <v-list-item v-if="profile.id === defaultAccount.owner.id" @click="openDial">
                    <v-list-item-icon>
                        <v-icon>mdi-plus</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title @click="">Добавить категорию</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-card>
    </div>
</template>

<script>
    import CategoryDialog from "../dialogs/CategoryDialog.vue";
    import CategoryTagsDialog from "../dialogs/CategoryTagsDialog.vue";
    import {mapState} from "vuex";

    export default {
        name: "CategoryList",

        components: {
            CategoryDialog,
            CategoryTagsDialog
        },

        data() {
            return {
                dialogCategory: false,
                dialogCategoryTags: false,
                selectedCategory: {}
            }
        },

        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
                profile: state => state.profile.profile,
                categories: state => state.categories.categories,
            }),
        },

        methods: {
            openDial() {
                this.dialogCategory = true
            },

            handleCloseCategoryDialog() {
                this.dialogCategory = false
            },

            handleCloseCategoryTagsDialog() {
                this.dialogCategoryTags = false
            },

            openCategoryTagsDial(category) {
                this.selectedCategory = category
                this.dialogCategoryTags = true
            },
        }
    }
</script>

<style scoped>

</style>