import React from 'react'
import {BsCardImage} from "react-icons/bs"
import "../../styles/placeholder.css"
const ImagePlaceHolder = () => {
  return (
    <div className='placeholder-container'>
      <BsCardImage className='icon'/>
      <div className="placeholder-title">This is a placeholder image.</div>
      <div className="sub-title">Please click this image and upload the relevant image.</div>
      <div className="requirements"></div>
    </div>
  )
}

export default ImagePlaceHolder
