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
                <span class="headline">Новый счет</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="Название счета" required v-model="name"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="Начальный баланс" hint="Сумма в рублях" required
                                          v-model="sum"></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>

            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Закрыть</v-btn>
                <v-btn color="blue darken-1" text @click="addVault">Сохранить</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import {mapState} from "vuex";
    import defaultAccount from "../../store/modules/defaultAccount";

    export default {
        name: "VaultDialog",

        props: {
            value: Boolean
        },

        data() {
            return {
                name: '',
                sum: '',
            }
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
                defaultAccount: state => state.defaultAccount.defaultAccount,
            }),
        },

        methods: {
            addVault() {
                const data = {name: this.name, sum: this.sum, accountId: this.defaultAccount.id};
                this.$store.dispatch('addVaultAction', data)
                this.name = ''
                this.sum = ''
                this.show = false
            },

            close() {
                this.show = false
            },
        },
    }
</script>

<style scoped>

</style>