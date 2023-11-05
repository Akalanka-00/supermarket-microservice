import React from 'react'
import NotFound from "../assets/images/notFound.jpg"

const PageNotFound = () => {
  return (
    <center style={{ margin: "5rem" }}>
      <h1>Page not found</h1>
      <img src={NotFound} width={800} style={{ margin: "2rem" }}/>
      <h2>404 ERROR</h2>
    </center>
  )
}

export default PageNotFound
