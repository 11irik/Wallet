<script>
    import {Pie} from "vue-chartjs";
    import {mapState} from "vuex";

    export default {
        extends: Pie,

        computed: {
            ...mapState({
                usersStats: state => state.usersStats.usersStats,
            }),
        },

        mounted() {
            let colors = []
            for (let i in this.usersStats[1]) {
                colors.push(this.getRandomColor());
            }

            this.renderChart(
                {
                    labels: this.usersStats[0],

                    datasets: [
                        {
                            backgroundColor: colors,
                            data: this.usersStats[1]
                        }
                    ]
                },
                {responsive: true, maintainAspectRatio: false}
            );
        },

        methods: {
            getRandomColor() {
                var letters = '0123456789ABCDEF';
                var color = '#';
                for (var i = 0; i < 6; i++) {
                    color += letters[Math.floor(Math.random() * 16)];
                }
                return color;
            }
        }
    };
</script>