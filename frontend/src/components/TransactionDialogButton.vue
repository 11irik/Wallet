<template>
    <v-dialog v-model="activator" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Новая транзакция</span>
            </v-card-title>
            <v-card-text>

                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="Описание" required v-model="description"></v-text-field>
                        </v-col>

                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="Сумма" hint="Сумма в рублях" required v-model="sum"></v-text-field>
                        </v-col>

                        <v-col cols="12" sm="6" md="4">
                            <DatePicker></DatePicker>
                        </v-col>

                        <v-col cols="12" sm="6" md="4">
                            <v-select
                                    :items="transactionTags"
                                    v-model="transactionTagId"
                                    item-text="description"
                                    item-value="id"
                                    label="Метка"
                                    required
                            ></v-select>
                        </v-col>

                        <v-col cols="12" sm="6" md="4">
                            <v-select
                                    :items="vaults"
                                    v-model="vaultId"
                                    item-text="name"
                                    item-value="id"
                                    label="Счет"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                </v-container>

            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Закрыть</v-btn>
                <v-btn color="blue darken-1" text @click="createTransaction">Сохранить</v-btn>
            </v-card-actions>
        </v-card>

        <template v-slot:activator="{ on }">
            <v-card-text style="height: 100px; position: relative">

                <v-btn
                        fixed
                        dark
                        fab
                        bottom
                        right
                        color="orange"
                        v-on="on"
                >
                    <v-icon>mdi-plus</v-icon>
                </v-btn>
            </v-card-text>
        </template>
    </v-dialog>
</template>

<script>
    import {mapState} from "vuex";
    import DatePicker from "./DatePicker.vue";

    export default {
        name: "TransactionDialog",

        components: {
            DatePicker
        },

        computed: {
            ...mapState({
                vaults: state => state.vaults.vaults,
                transactionTags: state => state.transactionTags.transactionTags,
                date: state => state.date.date,
            }),
        },

        data() {
            return {
                description: '',
                transactionTagId: '',
                vaultId: '',
                sum: '',
                selectedTransaction: '',
                activator: false
            }
        },

        methods: {
            close() {
                this.activator = false
            },

            createTransaction() {
                const dto = {
                    description: this.description,
                    vaultId: this.vaultId,
                    sum: this.sum,
                    date: this.date,
                    transactionTagId: this.transactionTagId
                };
                this.$store.dispatch('addTransactionAction', dto)

                this.close()
            },
        },
    }
</script>

<style scoped>

</style>