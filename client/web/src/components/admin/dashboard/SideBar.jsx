import React, { useEffect, useState } from "react";
import Logo from "../../../assets/icons/logo.png";
import "../../../styles/admin/sidebar.css";
import SideBarItems from "../../../context/AdminSideBarItems";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

const SideBar = () => {
  const navigate = useNavigate();
  const [activeItem, setActiveItem] = useState(0);
  const location = useLocation();
  const currentPath = location.pathname;
  const parts = currentPath.split("/");
  const lastElement = parts[3];

  useEffect(()=>{
    SideBarItems.map((item,index)=>{
      if(item.url === `/${lastElement}`){
        setActiveItem(index);
      }
    })
  })
  return (
    <div className="sidebar-container">
      <div className="header">
        <img src={Logo} />
        <span>FlashMart</span>
      </div>

      <div className="sidebar-items">
        {SideBarItems.map((item, index) => (
          <div
            className={`item ${activeItem === index && "active-item"}`}
            key={index}
            onClick={() => {
              setActiveItem(index);
              navigate(`/admin/dashboard${item.url}`);
            }}
          >
            <div className="icon">{item.icon}</div>
            <div className="title">{item.title}</div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SideBar;
