<template>
    <v-dialog v-model="show" persistent max-width="600px">
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
                        <!--                                FIXME-->

                        <!--                        <v-col cols="12" sm="6" md="4">-->
                            <!--                            <v-menu-->
                            <!--                                    v-model="menu"-->
                            <!--                                    :close-on-content-click="false"-->
                            <!--                                    transition="scale-transition"-->
                            <!--                                    offset-y-->
                            <!--                                    max-width="290px"-->
                            <!--                                    min-width="290px"-->
                            <!--                                <template v-slot:activator="{ on }">-->
                            <!--                                    <v-text-field-->
                            <!--                                            v-model="computedDateFormatted"-->
                            <!--                                            label="Дата"-->
                            <!--                                            hint="ММ-ДД-ГГГГ"-->
                            <!--                                            persistent-hint-->

                            <!--                                            readonly-->
                            <!--                                            v-on="on"-->
                            <!--                                    ></v-text-field>-->
                            <!--                                </template>-->
                            <!--                                <v-date-picker v-model="date" no-title @input="menu = false"></v-date-picker>-->
                            <!--                            </v-menu>-->
<!--                        </v-col>-->
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
    </v-dialog>

</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "TransactionDialog",

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
                vaults: state => state.vaults.vaults,
                transactionTags: state => state.transactionTags.transactionTags,
                tags: state => state.tags.tags,
                defaultAccount: state => state.defaultAccount.defaultAccount,
            }),
        },

        data() {
            return {
                description: '',
                transactionTagId: '',
                vaultId: '',
                sum: '',
                selectedTransaction: '',
            }
        },

        methods: {
            close() {
                this.show = false
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

            formatDate(date) {
                if (!date) return null

                const [year, month, day] = date.split('-')
                return `${month}/${day}/${year}`
            },

            parseDate(date) {
                if (!date) return null

                const [month, day, year] = date.split('/')
                return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
            },
        },
    }
</script>

<style scoped>

</style>