<template>
    <v-container>
        <v-card
                class="mx-auto"
                max-width="434"
                tile
        >
            <v-img
                    height="100%"
                    src="https://cdn.vuetifyjs.com/images/cards/server-room.jpg"
            >
                <v-row
                        align="end"
                        class="fill-height"
                >
                    <v-col
                            align-self="start"
                            class="pa-0"
                            cols="12"
                    >
                        <v-avatar
                                class="profile"
                                color="grey"
                                size="164"
                                tile
                        >
                            <v-img :src="profile.userpic"></v-img>
                        </v-avatar>
                    </v-col>
                    <v-col class="py-0">
                        <v-list-item
                                color="rgba(0, 0, 0, .4)"
                                dark
                        >
                            <v-list-item-content>
                                <v-list-item-title class="title">{{profile.name}}</v-list-item-title>
                                <v-list-item-subtitle>Пользователь</v-list-item-subtitle>
                                <v-list-item-subtitle>В сети: {{profile.lastVisit}}</v-list-item-subtitle>
                            </v-list-item-content>
                        </v-list-item>
                    </v-col>
                </v-row>
            </v-img>
        </v-card>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex';

    export default {
        name: 'Profile',
        computed: {
            ...mapState({
                profile: state => state.profile.profile,
            }),
        },
    };
</script>

<style scoped>
    img {
        max-width: 100%;
        height: auto;
    }
</style>
