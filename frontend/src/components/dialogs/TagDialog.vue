<template>
    <v-dialog v-model="visible" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Добавить метку</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="метка" required v-model="tagName"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                            <v-btn @click="addTag">Добавить</v-btn>
                        </v-col>
                    </v-row>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="onClose">Закрыть</v-btn>
                    </v-card-actions>
                </v-container>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    import {mapState} from 'vuex';

    export default {
        name: 'TagDialog',

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
                tagName: '',
            }
        },

        methods: {
            addTag() {
                let dto = {
                    'accountId': this.defaultAccount.id,
                    'name': this.tagName
                };

                this.$store.dispatch('addTagAction', dto);
                this.tagName = '';
                this.onClose();
            },
        },
    }
</script>

<style scoped>

</style>
