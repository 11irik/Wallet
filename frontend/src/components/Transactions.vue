<template>
    <div>
        <TransactionList :transactions="transactions" :onTransactionClick="openItemsDialog"/>
        <TransactionItemsDialog :visible="dialogItems" :onClose="handleCloseItemsDialog" :transaction="selectedTransaction"></TransactionItemsDialog>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import TransactionItemsDialog from './dialogs/TransactionItemsDialog.vue';
    import TransactionList from './lists/TransactionList.vue';

    export default {
        name: 'Transactions',

        components: {
            TransactionItemsDialog,
            TransactionList
        },

        data: () => ({
            dialogItems: false,
            selectedTransaction: {}
        }),

        computed: {
            ...mapState({
                transactions: state => state.transactions.transactions,
            }),
        },

        methods: {
            openItemsDialog(transaction) {
                this.$store.dispatch('retrieveItemsAction', transaction);
                this.selectedTransaction = transaction;
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
