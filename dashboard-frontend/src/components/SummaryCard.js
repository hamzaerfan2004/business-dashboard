export default function SummaryCard({ title, value }) {

    return (

        <div className="col-md-3 mb-3">

            <div className="card shadow-sm">

                <div className="card-body">

                    <h6 className="text-muted">

                        {title}

                    </h6>

                    <h2>

                        {value}

                    </h2>

                </div>

            </div>

        </div>

    );

}