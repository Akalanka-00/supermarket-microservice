import React, { useState } from "react";
import { categoryItems, productItems } from "../../../../context/Products";
import {BsPencil} from "react-icons/bs";
import {AiOutlineEye} from "react-icons/ai"
import { useNavigate } from "react-router-dom";
import CategoryModal from "../../../modals/CategoryModal";

const Category = () => {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);
  const openCategoryModal = ()=>setModalOpen(true);

  const closeCategoryModal = ()=>setModalOpen(false);
  const [category, setCategory] = useState(  { name: "", image: "", id:""},
  )
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
              <div className="value category-type">{item.name}</div>
            </div>
            </div>

            <div className="data">
              <div className="name">No. of products: </div>
              <div className="value">{productItems.filter((pitem)=>pitem.category===item.id).length} </div>
            </div>

          
              <div className="options">
              <div className="btn option" onClick={()=>{
                setCategory(item);
                openCategoryModal();
              }}><span>Edit</span> </div>
              <div className="btn option" onClick={()=>navigate(`/admin/dashboard/category/${item.id}`)}><span>View</span> </div>
              </div>
          </div>
        ))}
      </div>
      {modalOpen && <CategoryModal closeModal={closeCategoryModal} data={category}/>}
    </section>
  );
};

export default Category;
