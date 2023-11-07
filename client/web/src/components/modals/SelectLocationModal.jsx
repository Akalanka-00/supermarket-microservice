import React from 'react'

const SelectLocationModal = ({ closeModal, data }) => {
    const {lat, lon} = data;
    console.log(data);
    const zoom = 16;

    
  return (
    <div className="modal-container">
    <div className="modal-header">
      <div className="modal-title">Category</div>
      <div className="btn-close" onClick={closeModal}>
        X
      </div>
    </div>

    <div className="modal-body">
    <div className="modal-map">
    <iframe src={`https://maps.google.com/maps?q=${lat},${lon}&z=${zoom}&output=embed`}
      width={"600"}
      height={"300"}
      loading='lazy'
      referrerPolicy='no-referrer-when-downgrade'
      title='google map'

      ></iframe>

      <div className="btn">Select Location</div>
    </div>
       
    </div>
  </div>
  )
}

export default SelectLocationModal
