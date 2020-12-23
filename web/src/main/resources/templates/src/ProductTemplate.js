import './App.css';
import React,{useContext} from 'react';
import contx from "./Context";

export default function ProductTemplate({product}) {
    const{RemoveProd}=useContext(contx)
    return (<React.StrictMode><tr><td className="product-list">
        <div className="cart-product-item d-flex align-items-center">
            <div className="remove-icon">
                <button className="rm" onClick={RemoveProd.bind(null,product.id)}>&times;</button>
            </div>
            <a href="single-product.html" className="product-thumb">
                <img alt="Product" src={product.linc}/>
            </a>
            <a href="single-product.html" className="product-name"
            >{product.Name}</a>
        </div>
    </td>
    <td>
        <span className="price">{product.Price}</span>
    </td></tr></React.StrictMode>)};