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

                    <!--                        {{items}}-->
                    <!--                    <v-list :key="keyUp">-->
                    <!--                        <v-toolbar-title>Покупки</v-toolbar-title>-->
                    <!--                        <v-list-item-->
                    <!--                                v-for="item in itemList"-->
                    <!--                                :key="item.id"-->
                    <!--                                @click="deleteItem(item)"-->
                    <!--                        >-->
                    <!--                            <v-list-item-content>-->
                    <!--                                <v-list-item-title>Описание: {{item.name}}</v-list-item-title>-->
                    <!--                                <v-spacer></v-spacer>-->
                    <!--                                <v-list-item-title>цена: {{item.price}} р.</v-list-item-title>-->
                    <!--                                <v-list-item-subtitle v-if="item.tag">метка: {{item.tag.name}}-->
                    <!--                                </v-list-item-subtitle>-->
                    <!--                            </v-list-item-content>-->
                    <!--                        </v-list-item>-->
                    <!--                    </v-list>-->
                </v-container>
            </v-card-text>
        </v-card>

    </v-dialog>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "TransactionItemsDialog",

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
            }),
        },

        data() {
            return {
                selectedTransaction: '',
                itemDescription: '',
                itemPrice: '',
                itemTag: '',
                itemList: []
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

                this.$store.dispatch('addTransactionItemAction', dto)

                this.itemDescription = ''
                this.itemPrice = ''
                this.itemTag = ''
                this.itemList = this.selectedTransaction.items
            },

            deleteItem(item) {

            },
        },
    }
</script>

<style scoped>

</style>