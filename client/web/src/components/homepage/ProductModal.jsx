import React from 'react'

const ProductModal = ({product}) => {
  return (
    <div className='productModal-container'>
      <div className="modal-header">
        <div className="title">{product.name}</div>
        <button>X</button>
      </div>
      <div className="modal-body">
        
      </div>
    </div>
  )
}

export default ProductModal
