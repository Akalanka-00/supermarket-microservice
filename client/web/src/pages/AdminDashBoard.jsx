import React from 'react'
import SideBar from '../components/admin/dashboard/SideBar'
import { Outlet } from "react-router-dom"

import "../styles/admin/dashboard.css"
const AdminDashBoard = () => {
  return (
    <div>
      <div className="admin-dashboard">
        <div className="left">
            <SideBar/>
        </div>
        <div className="right">
        <Outlet></Outlet>

        </div>
      </div>
    </div>
  )
}

export default AdminDashBoard
