<template>
    <div>
        <TagDialog v-model="dialogTag"></TagDialog>

        <v-card
                max-width="500"
                class="mx-auto"
        >
            <v-list>
                <v-list-item-title class="headline mb-1">Метки</v-list-item-title>

                <v-list-item
                        v-for="item in tags"
                        :key="item.id"
                        @click=""
                >
                    <v-list-item-content>
                        <v-list-item-title v-text="item.name"></v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-icon>
                        <v-icon>mdi-bookmark-outline</v-icon>
                    </v-list-item-icon>
                </v-list-item>
                <v-list-item v-if="profile.id === defaultAccount.owner.id" @click="openDial">
                    <v-list-item-icon>
                        <v-icon>mdi-plus</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title @click="">Добавить метку</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-card>
    </div>
</template>

<script>
    import TagDialog from "../dialogs/TagDialog.vue";
    import {mapState} from "vuex";

    export default {
        name: "TagList",

        components: {
            TagDialog
        },

        data() {
            return {
                dialogTag: false,
            }
        },

        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
                accounts: state => state.accounts.accounts,
                profile: state => state.profile.profile,
                tags: state => state.tags.tags,
                categories: state => state.categories.categories,
            }),
        },
        methods: {
            openDial() {
                this.dialogTag = true
            },
        }
    }
</script>

<style scoped>

</style>