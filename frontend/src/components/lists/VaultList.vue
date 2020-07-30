<template>
    <v-row>
        <v-col
                v-for="vault in vaults"
                :key=vault.id
                cols="12"
                md="4"
        >
            <v-card
                    class="mx-auto"
                    max-width="344"
                    outlined
            >
                <v-list-item three-line>
                    <v-list-item-content>
                        <v-list-item-title class="headline mb-1">{{vault.name}}</v-list-item-title>
                        <v-list-item-subtitle>Баланс: {{vault.sum}} р.</v-list-item-subtitle>
                    </v-list-item-content>

                    <v-list-item-avatar
                            tile
                            size="50"
                            color="indigo"
                    >
                        <v-icon dark>mdi-cash</v-icon>
                    </v-list-item-avatar>
                </v-list-item>

                <!--                    <v-card-actions>-->
                <!--                        <v-btn text>Открыть тразакции</v-btn>-->
                <!--                    </v-card-actions>-->
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "VaultList",

        computed: {
            ...mapState({
                vaults: state => state.vaults.vaults,
            }),
        },

        data() {
            return {
                dialog: false,
                name: '',
                sum: '',
            }
        },

        methods: {}
    }
</script>

<style scoped>

</style>