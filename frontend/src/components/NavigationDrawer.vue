<template>
    <div>
        <v-navigation-drawer
                v-model="show"
                absolute
                temporary
        >
            <v-list>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title>Группы</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item-group v-model="model" mandatory color="indigo">
                    <v-list-item
                            v-for="(account) in accounts"
                            :key="account.id"
                            @click="selectAccount(account)"
                    >
                        <v-list-item-icon>
                            <v-icon>mdi-bank</v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title v-text="account.name"></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>

                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-plus</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-subtitle @click="openDialogAccount">Новая группа</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>

        </v-navigation-drawer>
        <AccountDialog v-model="dialogAccount"></AccountDialog>
    </div>
</template>

<script>
    import AccountDialog from "./dialogs/AccountDialog.vue";
    import {mapState} from "vuex";

    export default {
        name: "NavigationDrawer",

        components: {
            AccountDialog,
        },

        props: {
            value: Boolean
        },

        data: () => ({
            drawer: null,
            model: 0,
            dialogAccount: false,
            accountName: '',
        }),

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
                accounts: state => state.accounts.accounts
            }),

        },

        methods: {
            selectAccount(account) {
                this.$store.dispatch('retrieveCategoryAction', account)
                this.$store.dispatch('retrieveTagsAction', account)
                this.$store.dispatch('retrieveTransactionTagsAction', account)
                this.$store.dispatch('retrieveVaultsAction', account)
                this.$store.dispatch('selectAccountAction', account)

                this.drawer = false
            },

            openDialogAccount() {
                this.dialogAccount = true
            }
        },
    }
</script>

<style scoped>

</style>