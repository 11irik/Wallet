<template>
    <v-container>
        <v-toolbar-title>Транзакции</v-toolbar-title>

        <RangeDatePicker/>
        <v-btn @click="getTransactions">Найти</v-btn>

        <TransactionList/>

        <TransactionDialogButton/>
    </v-container>
</template>

<script>

    import TransactionDialogButton from '../components/TransactionDialogButton.vue';
    import RangeDatePicker from '../components/RangeDatePicker.vue';
    import TransactionList from '../components/lists/TransactionList.vue';
    import {mapState} from 'vuex';

    export default {
        name: 'Transactions',

        components: {
            TransactionDialogButton,
            RangeDatePicker,
            TransactionList,
        },

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
                this.$store.dispatch('retrieveTransactionsAction', dto);
            },
        },

        created() {
            this.getTransactions();
        },

    }
</script>

<style scoped>

</style>