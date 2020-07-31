<template>
    <v-dialog
            ref="dialog"
            v-model="modal"
            :return-value.sync="date"
            persistent
            width="290px"
    >
        <template v-slot:activator="{ on, attrs }">
            <v-text-field
                    v-model="date"
                    label="Picker in dialog"
                    readonly
                    v-bind="attrs"
                    v-on="on"
            ></v-text-field>
        </template>
        <v-date-picker v-model="date" range no-title scrollable>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="modal = false">Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.dialog.save(date)">OK</v-btn>
        </v-date-picker>
    </v-dialog>
</template>

<script>
    import {mapState} from "vuex";
    import store from "../store/store";

    export default {
        name: "RangeDatePicker",

        computed: {
            date: {
                get: () => {
                    return store.getters.dates
                },
                set: (newValue) => {
                    store.dispatch('setDatesAction', newValue)
                }
            },
        },

        methods: {},

        data: () => ({
            modal: false,
        }),
    }
</script>

<style scoped>
</style>