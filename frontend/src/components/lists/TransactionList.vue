<template>
    <div>
        <v-list>
            <v-list-item
                    v-for="transaction in transactions"
                    :key="transaction.id"
                    @click="openItemsDialog(transaction)"
            >

                <v-list-item-avatar v-if="!transaction.transactionTag.getting">
                    <v-icon>
                        mdi-minus-circle-outline
                    </v-icon>
                </v-list-item-avatar>

                <v-list-item-avatar v-if="transaction.transactionTag.getting">
                    <v-icon>
                        mdi-plus-circle-outline
                    </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                    <v-list-item-title>Описание: {{transaction.description}}</v-list-item-title>
                    <v-list-item-title>Счет: {{transaction.vault.name}}</v-list-item-title>

                    <v-spacer></v-spacer>

                    <v-list-item-title>Сумма: {{transaction.sum}} р.</v-list-item-title>
                    <v-list-item-subtitle>Дата: {{transaction.date}}</v-list-item-subtitle>
                </v-list-item-content>

            </v-list-item>
        </v-list>

        <TransactionItemsDialog :visible="dialogItems" :onClose="handleCloseItemsDialog"></TransactionItemsDialog>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import TransactionItemsDialog from '../dialogs/TransactionItemsDialog.vue';

    export default {
        name: 'TransactionList',

        components: {
            TransactionItemsDialog,
        },

        data: () => ({
            dialogItems: false,
        }),

        computed: {
            ...mapState({
                transactions: state => state.transactions.transactions,
            }),
        },

        methods: {
            openItemsDialog(transaction) {
                this.$store.dispatch('selectTransactionAction', transaction);
                this.$store.dispatch('retrieveItemsAction', transaction);
                this.dialogItems = true;
            },

            handleCloseItemsDialog() {
                this.dialogItems = false;
            }
        },
    }
</script>

<style scoped>
</style>
