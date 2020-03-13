import * as React from "react";
import { Theme, createStyles, withStyles, WithStyles } from "@material-ui/core"
import withRoot from "./../../withRoot";

const styles = (theme: Theme) =>
  createStyles
  ({
    container:
    {
      display: "flex",
      flexGrow: 1,
      justifyContent: "center",
      minHeight: 50,
      alignItems: "center",
      color: "orange"
    },
  });

interface IState
{}

interface IProps
{}

  class Footer extends React.Component<IProps & WithStyles<typeof styles>, IState>
  {
    render()
    {
        const css = this.props.classes;

        const Body = () =>
            <div className={css.container}>
                Copyright BringaWebShop 2020. All rights reserved.
            </div>

        return Body();
    }
  }

const FooterComponent = withRoot(withStyles(styles)(Footer));
export default FooterComponent;