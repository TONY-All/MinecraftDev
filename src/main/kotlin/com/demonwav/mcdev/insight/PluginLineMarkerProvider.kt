/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.insight

import com.demonwav.mcdev.asset.GeneralAssets
import com.demonwav.mcdev.asset.MCMessages
import com.demonwav.mcdev.facet.MinecraftFacet
import com.intellij.codeHighlighting.Pass
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.psi.PsiElement
import com.intellij.util.FunctionUtil

class PluginLineMarkerProvider : LineMarkerProviderDescriptor() {

    override fun getName() = MCMessages["plugin_marker.name"]

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        val module = ModuleUtilCore.findModuleForPsiElement(element) ?: return null

        val instance = MinecraftFacet.getInstance(module) ?: return null

        if (!instance.shouldShowPluginIcon(element)) {
            return null
        }

        return LineMarkerInfo(
            element,
            element.textRange,
            GeneralAssets.PLUGIN,
            Pass.UPDATE_ALL,
            FunctionUtil.nullConstant<PsiElement, String>(), null,
            GutterIconRenderer.Alignment.RIGHT
        )
    }

    override fun collectSlowLineMarkers(elements: List<PsiElement>, result: Collection<LineMarkerInfo<*>>) {}
}
