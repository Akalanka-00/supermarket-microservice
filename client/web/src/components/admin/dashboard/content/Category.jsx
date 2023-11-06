import React, { useState } from "react";
import { categoryItems } from "../../../../context/Products";
import {BsPencil} from "react-icons/bs";
import {AiOutlineEye} from "react-icons/ai"
import { useNavigate } from "react-router-dom";
import CategoryModal from "../../../modals/CategoryModal";

const Category = () => {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);
  const openCategoryModal = ()=>setModalOpen(true);

  const closeCategoryModal = ()=>setModalOpen(false);
  const [category, setCategory] = useState({ name: "", image: null, noOfProducts:0})
  return (
    <section className="category-container">
      <div className="header">
        <div className="title">Product Categories</div>
        <div className="btn" onClick={openCategoryModal}>Add Category</div>
      </div>
      <div className="category-list">
        {categoryItems.map((item, index) => (
          <div className="category-item" key={index}>
            <div className="info">
            <img src={item.image} />
            <div className="data">
              <div className="value category-type">Food</div>
            </div>
            </div>

            <div className="data">
              <div className="name">No. of products: </div>
              <div className="value">15 </div>
            </div>

          
              <div className="options">
              <div className="btn option"><span>Edit</span> </div>
              <div className="btn option" onClick={()=>navigate(`/admin/dashboard/category/${index}`)}><span>View</span> </div>
              </div>
          </div>
        ))}
      </div>
      {modalOpen && <CategoryModal closeModal={closeCategoryModal} data={category}/>}
    </section>
  );
};

export default Category;
