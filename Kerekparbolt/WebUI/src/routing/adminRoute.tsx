import * as React from "react";
import { useLocation } from "react-router-dom";
import { Redirect, Route } from "react-router";

import { ProtectedRouteProps } from "./protectedRouteProps";
import { isAdmin } from "./../services/client/roleService";

/*
usege: <ProtectedRoute {...defaultProtectedRouteProps} exact={true} path="/" component = { ProtectedContainer } />
use it in route.tsx
*/

export function AdmindRoute(props: ProtectedRouteProps)
{
    const location = useLocation();
    if (props.path !== location.pathname)
    {
        return null;
    }

    if (!isAdmin())
    {
        return <Redirect to={{ pathname: props.authenticationPath }}/>;
    }
    else
    {
        return <Route {...props}/>;
    }
}
