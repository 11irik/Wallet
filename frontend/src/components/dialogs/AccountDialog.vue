<template>
    <v-dialog v-model="visible" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Новая группа</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="Название группы" required v-model="accountName"></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>

            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="onClose">Закрыть</v-btn>
                <v-btn color="blue darken-1" text @click="addAccount">Сохранить</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import {mapState} from 'vuex';

    export default {
        name: 'AccountDialog',

        props: {
            visible: Boolean,
            onClose: Function,
        },

        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
            }),
        },

        data() {
            return {
                accountName: '',
            }
        },

        methods: {
            addAccount() {
                let acc = {name: this.accountName};
                this.$store.dispatch('addAccountAction', acc);
                this.accountName = '';
                this.onClose();
            },
        },
    }
</script>

<style scoped>

</style>
