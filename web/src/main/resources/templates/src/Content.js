import './App.css';
import React from 'react';
import ProductTemplate from "./ProductTemplate";

export default function Content(props) {
    return (
            <div class="page-content-wrapper sp-y">
                <div class="cart-page-content-wrap">
                    <div class="container container-wide">
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="shopping-cart-list-area">
                                    <div class="shopping-cart-table table-responsive">
                                        <table class="table table-bordered text-center mb-0">
                                            <thead>
                                            <tr>
                                                <th>Products</th>
                                                <th>Price</th>

                                            </tr>
                                            </thead>
                                            {props.products.length ? (
                                            <tbody>

                                                {props.products.map(pr=>{
                                                    return <ProductTemplate product={pr}/>
                                                })}


                                            </tbody>):(<tbody><p>Card clear</p></tbody>)}
                                        </table>
                                    </div>

                                    <div
                                        class="cart-coupon-update-area d-sm-flex justify-content-between align-items-center">
                                        <div class="coupon-form-wrap">
                                            <form action="#" method="post">
                                                <label for="coupon" class="sr-only">Coupon Code</label>
                                                <input type="text" id="coupon" placeholder="Coupon Code"/>
                                                <button class="btn-apply" >Apply Button</button>
                                            </form>
                                        </div>

                                        <div class="cart-update-buttons mt-15 mt-sm-0">
                                            <button class="btn-clear-cart">Clear Cart</button>
                                            <button class="btn-update-cart" onClick={props.upd.bind(null)}>Update Cart</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4">
                                <div class="cart-calculate-area mt-sm-40 mt-md-60">
                                    <h5 class="cal-title">Cart Totals</h5>

                                    <div class="cart-cal-table table-responsive">
                                        <table class="table table-borderless">
                                            <tr class="cart-sub-total">
                                                <th>Payment metod</th>
                                                <td>
                                                    <select class="form-select" id="payment"
                                                            aria-label="Default select example">
                                                        <option selected>Choose payment type</option>
                                                        <option value="1">Internal account</option>
                                                        <option value="2">Debit</option>
                                                        <option value="3">WebMoney</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr class="shipping">
                                                <th><label htmlFor="floatingInput">Email address</label></th>
                                                <td >
                                                    <div class="form-floating mb-3">
                                                        <input type="email" className="form-control" id="floatingInput"
                                                               placeholder="name@example.com"/>

                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="order-total">
                                                <th>Total</th>
                                                <td><b>$<b>{props.summ}</b></b></td>
                                            </tr>
                                        </table>
                                    </div>

                                    <div class="proceed-checkout-btn">
                                        <button id="redy"><a href="#" id="krya" class="btn btn-brand d-block">Proceed to
                                            Checkout</a></button>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

)};
