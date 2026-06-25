import {
    PieChart,
    Pie,
    Tooltip,
    Legend,
    Cell,
    ResponsiveContainer
} from "recharts";

const COLORS = [
    "#0088FE",
    "#00C49F",
    "#FFBB28",
    "#FF8042",
    "#AA66CC"
];

export default function CategoryPieChart({ data }) {

    return (

        <ResponsiveContainer
            width="100%"
            height={350}
        >

            <PieChart>

                <Pie
                    data={data}
                    dataKey="total"
                    nameKey="category"
                    outerRadius={120}
                    label
                >

                    {data.map((entry, index) => (

                        <Cell
                            key={index}
                            fill={COLORS[index % COLORS.length]}
                        />

                    ))}

                </Pie>

                <Tooltip />

                <Legend />

            </PieChart>

        </ResponsiveContainer>

    );

}