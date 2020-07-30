<template>
    <v-container>
        <div id="app">
            <v-row>
                <v-col cols="12" sm="3">
                    <DatePicker></DatePicker>
                    <v-btn @click="getData">Найти</v-btn>
                </v-col>
                <v-card-title>Всего транзакций за период совершено на: {{sum}} р.</v-card-title>

            </v-row>

            <v-toolbar-title>Диаграмма по категориям за период</v-toolbar-title>
            <PieChart :key="pieKey"/>

            <v-toolbar-title>Диаграмма по меткам за период</v-toolbar-title>
            <BarChart :key="barKey"/>

            <v-toolbar-title>Диаграмма по транзакциям в день за период</v-toolbar-title>
            <LineChart :key="lineKey"/>

            <v-toolbar-title>Диаграмма по пользователям за период</v-toolbar-title>
            <PieChartUser :key="userPieKey"/>
        </div>
    </v-container>
</template>

<script>
    import PieChart from "components/charts/PieChart.vue";
    import BarChart from "components/charts/BarChart.vue";
    import LineChart from "components/charts/LineChart.vue"
    import PieChartUser from "components/charts/PieChartUser.vue";
    import DatePicker from "../components/DatePicker.vue";
    import {mapGetters, mapState} from "vuex";


    export default {
        name: "App",
        components: {
            PieChart,
            BarChart,
            LineChart,
            PieChartUser,
            DatePicker
        },

        created: function () {
            this.getData()
        },


        data() {
            return {
                menu1: false,
                pieKey: 0,
                barKey: 1,
                lineKey: 2,
                userPieKey: 3,
            };
        },

        computed: {
            sum() {
                let sum = 0.0
                if (this.usersStats[1] != null) {
                    let a = this.usersStats[1]
                    a.forEach(element => sum += element)
                }
                return sum
            },
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
                usersStats: state => state.usersStats.usersStats,
                dates: state => state.dates.dates
            }),
        },

        methods: {
            getData() {

                var dto

                if (this.dates[0] > this.dates[1]){
                    dto = {
                        accountId: this.defaultAccount.id,
                        dateStart: this.dates[1],
                        dateEnd: this.dates[0]
                    }
                }
                else {
                    dto = {
                        accountId: this.defaultAccount.id,
                        dateStart: this.dates[0],
                        dateEnd: this.dates[1]
                    }
                }

                this.$store.dispatch('retrieveUsersStatsAction', dto)
                this.$store.dispatch('retrieveTagsStatsAction', dto)
                this.$store.dispatch('retrieveDaysStatsAction', dto)
                this.$store.dispatch('retrieveCategoriesStatsAction', dto)


                this.sleep(200).then(() => {
                    this.barKey += 1
                    this.lineKey += 1
                    this.userPieKey += 1
                    this.pieKey += 1
                })


            },

            sleep(ms) {
                return new Promise(resolve => setTimeout(resolve, ms));
            }

        }
    };
</script>

<style>
    #app {
        font-family: "Avenir", Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
