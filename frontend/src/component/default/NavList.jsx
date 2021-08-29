import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import { FaBars } from "react-icons/fa";

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
          <ul className="nav-wrap">
            <li>
              <a href="#">home</a>
            </li>
            <li>
              <a href="#">공지사항</a>
            </li>
            <li>
              <a href="#">스터디</a>
            </li>
            <li>
              <a href="#">컨텐츠</a>
            </li>
            <li>
              <a href="#">회원 관리</a>
            </li>
          </ul>
        </Default>
        <Mobile>
          <div id="mysidenav" className="nav-wrap-sidenav mobile">
            <a href="#" className="closebtn" onClick={this.closeNav}>
              x
            </a>
            <a href="#">카테고리1</a>
            <a href="#">카테고리2</a>
            <a href="#">카테고리3</a>
            <a href="#">카테고리4</a> {/* a태그 변경 필요 */}
            <a href="#">카테고리5</a>
          </div>

          <div class="new-wrap mobile">
            <div class="nav-wrap-mobile mobile" onClick={this.openNav}>
              <FaBars />
            </div>
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default NavList;
