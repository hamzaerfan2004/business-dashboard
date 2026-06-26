import {BrowserRouter,Routes,Route} from "react-router-dom";

import Login from "./pages/Login";
import Upload from "./pages/Upload";
import Register from "./pages/Register";

function App(){

    return(

        <BrowserRouter>

            <Routes>

                <Route path="/" element={<Login/>}/>
                
                <Route path="/register" element={<Register/>}/>

                <Route path="/upload" element={<Upload/>}/>

                

            </Routes>

        </BrowserRouter>

    );

}

export default App;