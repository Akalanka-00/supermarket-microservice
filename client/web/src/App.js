import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import PageNotFound from "./pages/PageNotFound";
import CustomerLogin from "./components/customer/regeistration/CustomerLogin";
import CustomerRegister from "./components/customer/regeistration/CustomerRegister";
import OTPVerification from "./components/customer/regeistration/OTPVerification";
import Cart from "./pages/Cart";
import Payment from "./components/customer/Payment";
import AdminLogin from "./components/admin/regeistration/AdminLogin";
import AdminDashBoard from "./pages/AdminDashBoard";
import Category from "./components/admin/dashboard/content/Category";
import Products from "./components/admin/dashboard/content/Products";
import Delivery from "./components/admin/dashboard/content/Delivery";
import Dashboard from "./components/admin/dashboard/content/Dashboard";
import LocateOnMap from "./components/admin/dashboard/content/LocateOnMap";
import LoadingScreen from "./components/shared/LoadingScreen";
import Discount from "./components/admin/dashboard/content/Discount";

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
        <Route path="/customer/cart/payment" element={<Payment/>}/>

        <Route path="/admin/login" element={<AdminLogin/>}/>
        <Route path="/admin/dashboard/*" element={<AdminDashBoard/>}>
        <Route index element={<Dashboard/>}/>

        <Route path="category/" element={<Category/>}/>
        <Route path="category/:id" element={<Products/>}/>

        <Route path="delivery/" element={<Delivery/>}/>
        <Route path="delivery/map" element={<LocateOnMap/>}/>

        <Route path="discounts/" element={<Discount/>}/>


        </Route>
      </Route>
      <Route path="/loading" element={<LoadingScreen/>}/>


      <Route path="/*" element={<PageNotFound/>}/>
      </Routes></BrowserRouter>
    </div>
  );
}

export default App;
