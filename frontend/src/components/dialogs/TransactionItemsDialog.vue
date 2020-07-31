<template>
    <v-dialog v-model="show" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Список покупок</span>
            </v-card-title>
            <v-card-text>

                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field label="Описание" required v-model="itemDescription"></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field label="Цена" required v-model="itemPrice"></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-select
                                    :items="tags"
                                    v-model="itemTag"
                                    item-text="name"
                                    item-value="id"
                                    label="Метка"
                                    required
                            ></v-select>
                        </v-col>

                        <v-row>
                            <v-col cols="6">
                                <v-btn @click="addItem">Добавить</v-btn>
                            </v-col>
                            <v-col cols="6">
                                <v-btn @click="close">Закрыть</v-btn>
                            </v-col>
                        </v-row>
                    </v-row>


                    <ItemList></ItemList>


                </v-container>
            </v-card-text>
        </v-card>

    </v-dialog>
</template>

<script>
    import {mapState} from "vuex";
    import ItemList from "../lists/ItemList.vue";

    export default {
        name: "TransactionItemsDialog",

        components: {
            ItemList
        },

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
                tags: state => state.tags.tags,
                defaultAccount: state => state.defaultAccount.defaultAccount,
                selectedTransaction: state => state.selectedTransaction.selectedTransaction
            }),
        },

        data() {
            return {
                itemDescription: '',
                itemPrice: '',
                itemTag: '',
            }
        },

        methods: {
            close() {
                this.show = false
            },

            addItem() {
                const dto = {
                    transactionId: this.selectedTransaction.id,
                    itemName: this.itemDescription,
                    itemPrice: this.itemPrice,
                    tagId: this.itemTag
                };

                this.$store.dispatch('addItemAction', dto)

                this.itemDescription = ''
                this.itemPrice = ''
                this.itemTag = ''
            },

            deleteItem(item) {

            },
        },
    }
</script>

<style scoped>

</style>