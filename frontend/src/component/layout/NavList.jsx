import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import { FaBars } from "react-icons/fa";
import { Link } from "react-router-dom";
import style from "../../css/header.module.css";

class NavList extends PureComponent {
  constructor(props) {
    super(props);
    this.openNav = this.openNav.bind(this);
    this.closeNav = this.closeNav.bind(this);
  }
  openNav = () => {
    document.getElementById("mysidenav").style.width = "250px";
  };
  closeNav = () => {
    document.getElementById("mysidenav").style.width = "0";
  };
  render() {
    return (
      <React.Fragment>
        <Default>
          <ul className={style['nav-wrap']}>
            <li>
              <Link to="/">home</Link>
            </li>
            <li>
              <Link to="/">공지사항</Link>
            </li>
            <li>
              <Link to="/study/recruit">스터디</Link>
            </li>
            <li>
              <Link to="/">컨텐츠</Link>
            </li>
            <li>
              <Link to="/">회원 관리</Link>
            </li>
          </ul>
        </Default>
        <Mobile>
          <div id="mysidenav" className={style['nav-wrap-sidenav']}>
            <a href="#!" className={style.closebtn} onClick={this.closeNav}>
              x
            </a>
            <Link to="/">카테고리1</Link>
            <Link to="/">카테고리2</Link>
            <Link to="/">카테고리3</Link>
            <Link to="/">카테고리4</Link>
            <Link to="/">카테고리5</Link>
          </div>

          <div className={style['new-wrap']}>
            <div className={style['nav-wrap-mobile']} onClick={this.openNav}>
              <FaBars />
            </div>
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default NavList;
