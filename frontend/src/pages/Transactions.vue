<template>
    <v-container>

        <!--            <v-toolbar-title>Транзакции</v-toolbar-title>-->

        <DatePicker></DatePicker>

        <v-btn @click="getTransactions">Найти</v-btn>


        <TransactionList></TransactionList>

        <TransactionDialog v-model="dialog"></TransactionDialog>
    </v-container>
</template>

<script>

    import TransactionDialog from "../components/dialogs/TransactionDialog.vue";
    import DatePicker from "../components/DatePicker.vue";
    import TransactionList from "../components/lists/TransactionList.vue";
    import {mapState} from "vuex";

    export default {
        name: "Transactions",

        components: {
            TransactionDialog,
            DatePicker,
            TransactionList,
        },

        data: () => ({
            dialog: false,
        }),

        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
                dates: state => state.dates.dates
            }),
        },

        methods: {
            getTransactions() {
                let dto = {
                    accountId: this.defaultAccount.id,
                    dateStart: this.dates[0],
                    dateEnd: this.dates[1]
                };
                this.$store.dispatch('retrieveTransactionsAction', dto)
            },
        },

        created() {
            this.getTransactions()
        }
    }
</script>

<style scoped>

</style>