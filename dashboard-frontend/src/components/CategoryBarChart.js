import {

BarChart,

Bar,

XAxis,

YAxis,

Tooltip,

CartesianGrid,

ResponsiveContainer

} from "recharts";

export default function CategoryBarChart({ data }) {

return (

<ResponsiveContainer
width="100%"
height={350}
>

<BarChart
data={data}
margin={{
top: 20,
right: 20,
left: 20,
bottom: 70
}}
>

<CartesianGrid strokeDasharray="3 3" />

<XAxis
dataKey="category"
interval={0}
angle={-30}
textAnchor="end"
/>

<YAxis />

<Tooltip />

<Bar dataKey="total" />

</BarChart>

</ResponsiveContainer>

);

}