<template>
    <div>
        <v-navigation-drawer
                v-model="visible"
                absolute
                temporary
        >
            <v-list>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title>Группы</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item-group mandatory color="indigo">
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

        <AccountDialog :visible="dialogAccount" :onClose="handleCloseAccountDialog"></AccountDialog>
    </div>
</template>

<script>
    import AccountDialog from './dialogs/AccountDialog.vue';
    import {mapState} from 'vuex';

    export default {
        name: 'NavigationDrawer',

        components: {
            AccountDialog,
        },

        props: {
            value: Boolean,
        },

        data: () => ({
            dialogAccount: false,
        }),

        computed: {
            visible: {
                get() {
                    return this.value;
                },
                set(value) {
                    this.$emit('input', value);
                }
            },
            ...mapState({
                accounts: state => state.accounts.accounts,
            }),
        },

        methods: {
            selectAccount(account) {
                this.$store.dispatch('retrieveCategoryAction', account);
                this.$store.dispatch('retrieveTagsAction', account);
                this.$store.dispatch('retrieveTransactionTagsAction', account);
                this.$store.dispatch('retrieveVaultsAction', account);
                this.$store.dispatch('selectAccountAction', account);
            },

            openDialogAccount() {
                this.dialogAccount = true;
            },

            handleCloseAccountDialog() {
                this.dialogAccount = false;
            },
        },
    }
</script>

<style scoped>

</style>
