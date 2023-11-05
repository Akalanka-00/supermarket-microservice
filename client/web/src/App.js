import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import PageNotFound from "./pages/PageNotFound";
import CustomerLogin from "./components/customer/regeistration/CustomerLogin";
import CustomerRegister from "./components/customer/regeistration/CustomerRegister";
import OTPVerification from "./components/customer/regeistration/OTPVerification";
import Cart from "./pages/Cart";

function App() {
  return (
    <div className="App">
     <BrowserRouter>
     <Routes>
      <Route path="/">
        <Route index element={<HomePage/>}/>
        <Route path="/customer/login" element={<CustomerLogin/>}/>
        <Route path="/customer/register" element={<CustomerRegister/>}/>
        <Route path="/customer/otp" element={<OTPVerification/>}/>

        <Route path="/customer/cart" element={<Cart/>}/>


      </Route>

      <Route path="/*" element={<PageNotFound/>}/>
      </Routes></BrowserRouter>
    </div>
  );
}

export default App;
