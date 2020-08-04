<template>
    <v-dialog v-model="visible" persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span class="headline">Пригласить пользователя</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="4">
                            <v-text-field label="email" required v-model="userEmail"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                            <v-btn @click="inviteUser">Добавить</v-btn>
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
        name: 'UserDialog',

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
                userEmail: '',
            }
        },

        methods: {
            inviteUser() {
                let dto = {
                    'accountId': this.defaultAccount.id,
                    'email': this.userEmail
                };

                this.$store.dispatch('inviteUserAction', dto)
                this.userEmail = ''
                this.onClose()
            },
        },
    }
</script>

<style scoped>

</style>
